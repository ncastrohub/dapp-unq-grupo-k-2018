import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../service/vehicle.service';
import { Vehicle } from '../vehicle'
import { Router } from '@angular/router';
// AGREGADO PARA AUTENTICACION
import { AuthService } from '../../auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})


export class ListComponent implements OnInit {

  constructor(private service: VehicleService, private router:Router, public authService: AuthService) { }
  loading = true;
  errorList = [];
  vehicleList: [Vehicle];

  ngOnInit() {
  	this.getVehicleList();
  }

  getVehicleList(){
    this.service.getVehicleList('1').subscribe(
      data => {
        this.vehicleList = data;
        this.loading = false;
      },
      error => {
        this.errorList.push(error);
        this.loading = false;
      }
    );
  }

  edit(vehicle:Vehicle){
    this.service.loadVehicleToEdit(vehicle);
    this.router.navigate(['/vehicle/edit']);
  }

  delete(vehicle:Vehicle){
    this.service.deleteVehicle('1', vehicle).subscribe(
      data => this.getVehicleList(),
      error => this.errorList.push(error)
    )
  }


}
