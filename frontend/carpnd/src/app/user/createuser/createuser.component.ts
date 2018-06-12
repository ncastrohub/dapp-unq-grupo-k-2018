import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';

// AGREGADO PARA AUTENTICACION
import { AuthService } from '../../auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.css']
})
export class CreateuserComponent {

// MODIFICADO PARA AUTENTICACION
 constructor(private service: UserServiceService, private router: Router, public authService: AuthService) {
// FIN MODIFICADO

    this.user = new User()
    this.user.name = authService.userProfile.name;
    this.user.email = authService.userProfile.email;
  }

  user:User;

  submitted = false;
  errorList = [];

  onSubmit() {
	  this.service.createUser(this.user).subscribe(
	    data => {
	      this.router.navigate(['/user/list']);
	    },
	    error => this.errorList.push(error)
	  );
  }

}
