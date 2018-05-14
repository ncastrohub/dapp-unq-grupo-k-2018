import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../service/vehicle.service';
import { Vehicle } from '../vehicle'
import { Router } from '@angular/router';


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

  edit(vehicle:Vehicle){
    this.service.editVehicle('1', vehicle).subscribe(
      data => this.router.navigate(['/vehicle/list']),
      error => this.errorList.push(error)
    );
  }


}
