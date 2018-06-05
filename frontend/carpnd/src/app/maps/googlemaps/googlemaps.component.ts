import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-googlemaps',
  templateUrl: './googlemaps.component.html',
  styleUrls: ['./googlemaps.component.css']
})

export class GooglemapsComponent implements OnInit {

  constructor() {}

  ngOnInit() {
  }

  lat: number;
  lng: number;
  locationChosen = false;

  setMarker(event) {
    this.lat = event.coords.lat;
    this.lng = event.coords.lng;
    this.locationChosen = true;
  }

}
