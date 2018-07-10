import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user.service';
import { Router } from '../../../node_modules/@angular/router';
import { NgForm } from '../../../node_modules/@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;

  constructor(private userService: UserService, private router: Router){

  }

  ngOnInit(){

  }

  login(f: NgForm): void{
      this.userService.login(f.value.username, f.value.password).subscribe((response) => {
        if (response != null) {
          sessionStorage.setItem("user", JSON.stringify(this.user));
          if(response.type == 1)
            this.router.navigateByUrl("/homeDriver");
          else if(response.type == 0)
            this.router.navigateByUrl("/homeOwner");
        } else
          console.log("LOGIN ERRATO");
      });
  }

}
