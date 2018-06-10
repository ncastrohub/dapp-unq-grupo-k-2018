import { Component, Output, EventEmitter } from '@angular/core';
import { Vehicle } from '../vehicle'
import { VehicleService } from '../service/vehicle.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateVehicleComponent {

  @Output() public onComplete: EventEmitter<any> = new EventEmitter();

  constructor(private service: VehicleService, private router: Router) {
    this.vehicle = new Vehicle();
  }
  
  vehicle:Vehicle;

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  submitted = false;
  errorList = [];

  onSubmit() {

    this.onComplete.emit({ event:event, vehicle: this.vehicle });

	  // this.service.createVehicle('1', this.vehicle).subscribe(
	  //   data => {
	  //     this.router.navigate(['/vehicle/list']);
	  //   },
	  //   error => this.errorList.push(error)
	  // );
  }

}
