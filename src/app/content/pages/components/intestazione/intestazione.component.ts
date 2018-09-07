import { Router } from '@angular/router';
import { Component, OnInit } from "@angular/core";
import { User } from '../../../../core/models/User';

@Component({
  selector: "app-intestazione",
  templateUrl: "./intestazione.component.html",
  styleUrls: ["./intestazione.component.scss"]
})

export class IntestazioneComponent implements OnInit {
  
  constructor(private router: Router) { 

  }

  ngOnInit() {

  }

  homeOrLogin(){
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    if(user != null){
      if(user.type == 1)
        this.router.navigateByUrl("/homeDriver");
      else if(user.type == 0)
        this.router.navigateByUrl("/homeOwner");
    }
    else
      this.router.navigateByUrl("/login");
  }
}
