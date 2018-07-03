import { Component, OnInit } from '@angular/core';
import { CreatePublication, Location, Days, MoneyAndAmount} from '../publication';
import { PublicationService } from '../publication.service';
import { Vehicle } from '../../vehicle/vehicle';
import { Router } from '@angular/router';
import { CreateVehicleComponent } from '../../vehicle/create/create.component';
import { GooglemapsComponent } from '../../maps/googlemaps/googlemaps.component';
import { DateSelectorComponent } from '../date-selector/date-selector.component';
import { CostDefinerComponent } from '../cost-definer/cost-definer.component';

// AGREGADO PARA AUTENTICACION
import { AuthService } from '../../auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css']
})

export class PublicationCreateComponent {

  loading = true;

  moment = "vehicle";
  publication = new CreatePublication();
  returnLocations = [];
  capacities = [1, 2, 3, 4, 5, 6, 7];
  carTypes = ['SEDAN', 'VAN'];
  daysOfWeek = [
    { name: "Sunday", value: 0},
    { name: "Monday", value: 1},
    { name: "Tuesday", value: 2},
    { name: "Wednesday", value: 3},
    { name: "Thursday", value: 4},
    { name: "Friday", value: 5},
    { name: "Saturday", value: 6}
  ]
  currencies = ['ARS']
  errorList = [];

  constructor(private service: PublicationService, private router: Router, public authService: AuthService) {
    this.publication = new CreatePublication();
    this.publication.disabledDays = [];
  }

  addReturnLocation($event) {
     this.returnLocations.push($event.location);
  }

  finishReturnLocation() {
    this.publication.returnLocations = this.returnLocations;
    this.moment = 'cost';
  }

  saveVehicle($event) {
  	this.publication.vehicle = $event.vehicle;
    this.moment = "acquire";
  }

  saveAcquireLocation($event) {
    this.publication.acquireLocation = $event.location;
    this.moment = "return";
  }

  saveCost($event) {
    this.publication.costPerHour = $event.cost;
    this.moment = "disabledDay";
  }



  addDisableDay($event, day_value){
    if($event.target.checked){
      this.publication.disabledDays.push(day_value)
    }else{
      let index = this.publication.disabledDays.indexOf(day_value, 0);
      if (index > -1) {
         this.publication.disabledDays.splice(index, 1);
      }
    }
  }

  publicationReady(){
    return this.publication.returnLocations && this.publication.vehicle && this.publication.acquireLocation && this.publication.costPerHour && this.publication.disabledDays;
  }

  createPublication() {
    this.loading = true;
	  this.service.createPublication('1', this.publication).subscribe(
	    data => {
	      this.router.navigate(['/publication/list']);
	    },
	    error => {
        this.loading = false;
        this.errorList.push(error)
      }
	  );
  }

}
