import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { ActivatedRoute } from '@angular/router';
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

  constructor(private authService: AuthService, private route: ActivatedRoute) {}
  ngOnInit() {

    let authorization_role = this.route.snapshot.paramMap.get('authorization_role');
    if (authorization_role == 'login') {
      this.authService.handleAuthenticationLogin();
    }
    if (authorization_role == 'sign_up') {
      this.authService.handleAuthenticationSignUp();
    }
  }

}
