import { Component, OnInit, Input } from '@angular/core';
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

//  constructor(private service: UserServiceService, private router: Router, public authService: AuthService) {
// // FIN MODIFICADO

//     this.user = new User();
// //Lee los datos del usuario
//     if (this.authService.isAuthenticated()){
//       // this.user.name = authService.userProfile.name;
//       // this.user.email = authService.userProfile.email;
//     }
// =======
constructor(private service: UserServiceService, private router: Router
            , public authService: AuthService) {}

  user : User;

  submitted = false;
  errorList = [];

  getUser() : void {
    if (this.authService.isAuthenticated()) {
      this.user = this.service.user;
    }
  }

  ngOnInit() {
     this.getUser();
  }
// FIN MODIFICADO

  onSubmit() {
	  this.service.updateUser(this.user).subscribe(
	    data => {
	      this.router.navigate(['/user/list']);
	    },
	    error => this.errorList.push(error)
	  );
  }

}
