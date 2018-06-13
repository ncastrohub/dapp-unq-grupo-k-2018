import { Component, OnInit, ElementRef, NgZone, Output, EventEmitter } from '@angular/core';
import { ViewChild } from '@angular/core';
// import {  } from '@types/googlemaps';
import { FormControl } from "@angular/forms";
import { MapsAPILoader } from '@agm/core';
import { Location } from '../../publication/publication';


@Component({
  selector: 'app-googlemaps',
  templateUrl: './googlemaps.component.html',
  styleUrls: ['./googlemaps.component.css']
})

export class GooglemapsComponent implements OnInit {

  public latitude: number;
  public longitude: number;
  public searchControl: FormControl;
  public zoom: number;
  private selectedLocation: Location;

  @Output() public onComplete: EventEmitter<any> = new EventEmitter();

  @ViewChild("search")
  public searchElementRef: ElementRef;
  
  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {}
  
  ngOnInit() {

    this.selectedLocation = new Location();

    //set google maps defaults
    this.zoom = 4;
    this.latitude = 39.8282;
    this.longitude = -98.5795;
        
  
    //create search FormControl
    this.searchControl = new FormControl();
    
    this.mapsAPILoader.load().then(() => {
      if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.selectedLocation.geoLatitude = position.coords.latitude;
          this.selectedLocation.geoLongitude = position.coords.longitude;
          this.zoom = 12;
          let geocoder = new google.maps.Geocoder;
          let infowindow = new google.maps.InfoWindow;
          
          let latlng = {lat: position.coords.latitude, 
            lng: position.coords.longitude};
          
          geocoder.geocode({'location': latlng}, (results, status)=> {
            this.selectedLocation.adressName = results[0].formatted_address;
          });
        });
      }

    })

    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();
  
          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          
          //set latitude, longitude and zoom
          this.latitude = place.geometry.location.lat();
          this.longitude = place.geometry.location.lng();
          this.zoom = 12;
          this.selectedLocation.adressName = place.formatted_address;
          this.selectedLocation.geoLatitude = this.latitude;
          this.selectedLocation.geoLongitude = this.longitude;

        });
      });
    });

  }
  
  onSubmit() {
    let newLocation = this.selectedLocation;
    this.selectedLocation = new Location();

    this.onComplete.emit({ event:event, location: newLocation });
  }

}
