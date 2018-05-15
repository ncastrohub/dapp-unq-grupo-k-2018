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

  constructor(private service: VehicleService, private router:Router) { }

  errorList = [];
  vehicleList: [Vehicle];
  
  ngOnInit() {
  	this.getVehicleList();
  }

  getVehicleList(){
    this.service.getVehicleList('1').subscribe(
      data => this.vehicleList = data,
      error => this.errorList.push(error)
    );
  }

  edit(vehicle:Vehicle){
    this.service.loadVehicleToEdit(vehicle);
    this.router.navigate(['/vehicles']);
  }

  delete(vehicle:Vehicle){
    this.service.deleteVehicle('1', vehicle).subscribe(
      data => this.getVehicleList(),
      error => this.errorList.push(error)
    )
  }


}
