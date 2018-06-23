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

  constructor(private service: UserServiceService, private router: Router
              , public authService: AuthService) {
       this.getUser();
  }
  
  user : User;
  submitted = false;
  errorList = [];

  getUser() : void {
    if (this.authService.isAuthenticated()) {
      this.user = this.service.user;
    }
  }

  ngOnInit() {
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
