import { Component, OnInit } from '@angular/core';
import { CreatePublication, Location, Days, MoneyAndAmount} from '../publication';
import { PublicationService } from '../publication.service';
import { Vehicle } from '../../vehicle/vehicle';
import { Router } from '@angular/router';
import { CreateVehicleComponent } from '../../vehicle/create/create.component';
import { GooglemapsComponent } from '../../maps/googlemaps/googlemaps.component';
import { DateSelectorComponent } from '../date-selector/date-selector.component';
import { CostDefinerComponent } from '../cost-definer/cost-definer.component'; 

@Component({
  selector: 'app-publication-create',
  templateUrl: './publication-create.component.html',
  styleUrls: ['./publication-create.component.css']
})

export class PublicationCreateComponent {

  moment = "vehicle";
  publication = new CreatePublication();
  returnLocations = [];
  capacities = [1, 2, 3, 4, 5, 6, 7];
  carTypes = ['SEDAN', 'VAN'];

  currencies = ['ARS']
  errorList = [];

  constructor(private service: PublicationService, private router: Router) {
    this.publication = new CreatePublication();
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
    this.moment = "complete";
  }

  createPublication() {
	  this.service.createPublication('1', this.publication).subscribe(
	    data => {
	      this.router.navigate(['/publication/list']);
	    },
	    error => this.errorList.push(error)
	  );
  }

}
