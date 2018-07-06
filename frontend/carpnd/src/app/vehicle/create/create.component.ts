import { Component, Output, EventEmitter } from '@angular/core';
import { Vehicle } from '../vehicle'
import { VehicleService } from '../service/vehicle.service';
import { Router } from '@angular/router';

// AGREGADO PARA AUTENTICACION
import { AuthService } from '../../auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-create-vehicle',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})

export class CreateVehicleComponent {

  loading = true;
  // @Output() public onComplete: EventEmitter<any> = new EventEmitter();
// MODIFICADO PARA AUTENTICACION
  constructor(private service: VehicleService, private router: Router, public authService: AuthService) {
// FIN MODIFICADO
    this.vehicle = new Vehicle();
    this.loading = false;
  }

  vehicle:Vehicle;

  capacities = [1, 2, 3, 4, 5, 6, 7];

  carTypes = ['SEDAN', 'VAN'];

  submitted = false;
  errorList = [];

  onSubmit($event) {
    this.loading = true;

	  this.service.createVehicle('1', this.vehicle).subscribe(
	    data => {
        this.loading = false;
	      this.router.navigate(['/vehicle/list']);
	    },
	    error => {
        this.errorList.push(error.error)
        this.loading = false;
      }
	  );
  }

}
