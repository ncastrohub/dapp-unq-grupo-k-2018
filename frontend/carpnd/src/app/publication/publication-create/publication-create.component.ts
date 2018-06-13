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
  constructor(private service: PublicationService, private router: Router) {
    this.publication = new CreatePublication();
    this.publication.vehicle = new Vehicle();
    this.publication.acquireLocation = new Location();
    this.publication.returnLocations = [];
    // this.publication.enabledDays = new Days();
    this.publication.costPerHour = new MoneyAndAmount();
  }

  publication:CreatePublication;

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  currencies = ['ARS']

  submitted = false;

  errorList = [];

  addDisableDay(day:number, month:number, year:number) {
 	this.publication.enabledDays.disabledDays.push([year,month,day])
  }

  addReturnLocation($event) {
 	  this.publication.returnLocations.push($event.location);
    this.moment = 'cost';
  }

  // addReservedDay(day:string, month:string, year:string) {
 	// this.publication.enabledDays.disabledDays.add([year,month,day])
  // }

  saveVehicle($event) {
  	this.publication.vehicle = $event.vehicle;
    this.moment = "acquire";  
  }

  saveAcquireLocation($event) {
    this.publication.acquireLocation = $event.location;
    this.moment = "return";  
  }


  // onSubmit() {
	 //  this.service.createPublication('1', this.publication).subscribe(
	 //    data => {
	 //      this.router.navigate(['/publication/list']);
	 //    },
	 //    error => this.errorList.push(error)
	 //  );
  // }

}
