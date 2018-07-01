import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vehicle } from '../vehicle';
import { Observable ,  BehaviorSubject } from 'rxjs';
import { AppConfig } from '../../config';

@Injectable()
export class VehicleService {

  private vehicleSource = new BehaviorSubject<Vehicle>(new Vehicle);
  vehicleObservable = this.vehicleSource.asObservable();

  constructor(private http: HttpClient, private config: AppConfig) { }

  getVehicleList(userId: string): Observable<[Vehicle]> {
  	return this.http.get<[Vehicle]>( this.config.serveUrl + 'publication/vehicle/list');
  }

  createVehicle(userId: string, vehicle: Vehicle): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/vehicle/new', vehicle);
  }

  updateVehicle(userId: string, vehicle: Vehicle): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/vehicle/update', vehicle);
  }
 	
 	deleteVehicle(userId: string, vehicle: Vehicle): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/vehicle/delete', vehicle);
  }

  loadVehicleToEdit(vehicle: Vehicle) {
    this.vehicleSource.next(vehicle);	
  }

}
