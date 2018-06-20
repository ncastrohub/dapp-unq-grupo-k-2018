import { Component, OnInit } from '@angular/core';
//import { User } from './user/user';

// AGREGADO PARA AUTENTICACION
import { AuthService } from './auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-callback',
  template: `
    <p>
      callback works!
    </p>
  `,
  styles: []
})
export class CallbackComponent implements OnInit {

  constructor(private authService: AuthService) {   // MODIFICADO PARA AUTENTICACION
  }

  ngOnInit() {
    this.authService.handleLoginCallback();
  }

}
