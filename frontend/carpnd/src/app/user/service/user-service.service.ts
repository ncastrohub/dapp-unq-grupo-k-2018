import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';
import { Observable } from 'rxjs/Observable';
import { AppConfig } from '../../config';

@Injectable()
export class UserServiceService {

  // private vehicleSource = new BehaviorSubject<Vehicle>(new Vehicle);
  // vehicleObservable = this.vehicleSource.asObservable();

  constructor(private http: HttpClient, private config: AppConfig) { }

  getUserList(): Observable<[User]> {
  	return this.http.get<[User]>( this.config.serveUrl + 'publication/user/list');
  }

  createUser(user:User): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/user/new', user);
  }

}
