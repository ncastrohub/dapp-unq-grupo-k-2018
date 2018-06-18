import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { VehicleService } from '../service/vehicle.service';
import { Vehicle } from '../vehicle'

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

	@Output() public onComplete: EventEmitter<any> = new EventEmitter();

  constructor(private service: VehicleService) { }

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
  complete(vehicle) {
    this.onComplete.emit({ vehicle: vehicle });
  }

}
