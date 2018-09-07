import { Component, OnInit, EventEmitter, Output } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router, NavigationExtras } from '@angular/router';
import { UserService } from "../../../../core/services/user.service";
import { User } from "../../../../core/models/User";

@Component({
  selector: "app-signup",
  templateUrl: "./signup.component.html",
  styleUrls: ["./signup.component.scss"]
})

export class SignupComponent implements OnInit {
  feedback: string;

  constructor(private userService: UserService, private router: Router) { 

  }

  ngOnInit() {

  }

  signup(f: NgForm): void{
    var user = new User(f.value.username, f.value.password, 1, f.value.name, f.value.surname, f.value.address, f.value.cap, f.value.handiccaped, f.value.phone, f.value.email);
    this.userService.signup(user).subscribe((response) => {
      if(response)
        this.feedback = "Registrazione effettuata con successo";
      else
        this.feedback = "Registrazione non andata a buon fine";
    this.userService.changeFeedback(this.feedback);
    //this.router.navigateByUrl("/login");
    this.router.navigateByUrl("/");
    });


  }

  back()
  {
    this.router.navigate(["/"]);
  }
}
