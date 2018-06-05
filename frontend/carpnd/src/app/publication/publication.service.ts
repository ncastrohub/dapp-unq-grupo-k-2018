import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config';
import { Observable } from 'rxjs/Observable';
import { Publication } from './publication';
import { Page } from './publication';


@Injectable()
export class PublicationService {

  constructor(private http: HttpClient, private config: AppConfig) { }

  getUserList(): Observable<Page<Publication>> {
  	return this.http.get<Page<Publication>>( this.config.serveUrl + 'publication/list/');
  }

}
