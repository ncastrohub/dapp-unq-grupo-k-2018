import { Component, OnInit } from '@angular/core';

import { Vehicle } from '../vehicle'
import { VehicleService } from '../service/vehicle.service';
import { Router } from '@angular/router';
import { Subscription }   from 'rxjs';

@Component({
  selector: 'app-vehicle',
  templateUrl: '../create/create.component.html',
  styleUrls: ['../create/create.component.css']
})

export class EditVehicleComponent implements OnInit {

  constructor(private service: VehicleService, private router: Router) { }

  loading = true;  
  vehicle:Vehicle;
  subscription: Subscription;

  ngOnInit() {
    this.subscription = this.service.vehicleObservable.subscribe(vehicle => this.vehicle = vehicle);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  submitted = false;
  errorList = [];

  onSubmit() {
    this.service.updateVehicle('1', this.vehicle).subscribe(
        data => {
          this.router.navigate(['/vehicle/list']);
          },
        error => this.errorList.push(error)
      );
    }
}
