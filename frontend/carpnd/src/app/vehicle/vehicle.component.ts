import { Component, OnInit } from '@angular/core';

import { Vehicle } from './vehicle';
import { VehicleService } from './service/vehicle.service'
import { Router } from '@angular/router';


@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  constructor(private service: VehicleService, private router: Router) { }
  
  vehicle:Vehicle;
  
  ngOnInit() {
     this.service.vehicleObservable.subscribe(vehicle => this.vehicle = vehicle);
  }

  ngOnDestroy() {
    this.service.cleanVehicle();
  }

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  submitted = false;
  errorList = [];

  onSubmit() {
    console.log(this.vehicle.id);
    if (this.vehicle.id == null) {
      this.service.createVehicle('1', this.vehicle).subscribe(
        data => {
          this.router.navigate(['/vehicle/list']);
          this.service.vehicleObservable
        },
        error => this.errorList.push(error)
      );
    }else{
      this.service.updateVehicle('1', this.vehicle).subscribe(
        data => {
          this.router.navigate(['/vehicle/list']);
          },
        error => this.errorList.push(error)
      );
    }
  }

}
