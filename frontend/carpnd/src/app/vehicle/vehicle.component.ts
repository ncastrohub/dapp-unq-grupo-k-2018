import { Component, OnInit } from '@angular/core';

import { Vehicle } from './vehicle';


@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  values = '';

  onKey(event: any) { // without type info
    this.values += event.target.value + ' | ';
  }


  model = new Vehicle(3, 'SEDAN', "Un auto lindo", 'http://www.google.com.ar/image.jpg');
  submitted = false;

  onSubmit() { this.submitted = true; }

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.model); }

}
