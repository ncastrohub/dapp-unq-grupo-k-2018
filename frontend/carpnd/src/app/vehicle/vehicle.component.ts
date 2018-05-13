import { Component, OnInit } from '@angular/core';

import { Vehicle } from './vehicle';
import {VehicleService} from './service/vehicle.service'

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  // onKey(event: any) { // without type info
  //   this.values += event.target.value + ' | ';
  // }


  vehicle = new Vehicle();
  submitted = false;

  onSubmit() { this.submitted = true; }

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.vehicle); }

}
