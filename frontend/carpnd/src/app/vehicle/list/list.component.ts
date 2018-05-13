import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../service/vehicle.service';
import { Vehicle } from '../vehicle'

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})


export class ListComponent implements OnInit {

  constructor(private service: VehicleService) { }

  errorList = [];
  vehicleList: [Vehicle];
  
  ngOnInit() {
  	this.service.getVehicleList('1').subscribe(
      data => this.vehicleList = data,
      error => this.errorList.push(error)
    );

  }

}
