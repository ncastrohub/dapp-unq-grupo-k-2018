import { Component } from '@angular/core';
import { } from '@types/googlemaps';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Lend your Car';
  constructor(public authService: AuthService) {}
}
