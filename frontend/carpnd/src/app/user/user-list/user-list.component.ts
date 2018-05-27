import { Component, OnInit } from '@angular/core';
import { User } from '../user'
import { Router } from '@angular/router';
import { UserServiceService } from '../service/user-service.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})


export class UserListComponent implements OnInit {

  errorList = [];
  userList: [User];
  
   constructor(private service: UserServiceService, private router:Router) { }

  ngOnInit() {
  	this.getUserList();
  }

  getUserList(){
    this.service.getUserList().subscribe(
      data => this.userList = data,
      error => this.errorList.push(error)
    );
  }

  edit(user:User){
    // this.service.loadVehicleToEdit(vehicle);
    // this.router.navigate(['/vehicle/edit']);
  }

  delete(user:User){
    this.service.deleteUser(user).subscribe(
      data => this.getUserList(),
      error => this.errorList.push(error)
    )
  }

}
