import { Component } from '@angular/core';
import { } from '@types/googlemaps';

// AGREGADO PARA AUTENTICACION
import { AuthService } from './auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Lend your Car';
  constructor(public authService: AuthService) {}
}
