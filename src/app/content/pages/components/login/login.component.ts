import { Component, OnInit, ChangeDetectorRef } from '@angular/core';

import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from '../../../../core/models/User';
import { UserService } from '../../../../core/services/user.service';
import { MenuConfigService } from '../../../../core/services/menu-config.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  feedback: string;
  user: User;

  constructor(private userService: UserService,
     private router: Router,
     private cdr: ChangeDetectorRef,
     private menuConfigService: MenuConfigService){

  }

  ngOnInit(){
    this.feedback = this.userService.feedback;
    this.userService.deleteFeedback();
  }

  login(f: NgForm): void{
      this.userService.login(f.value.username, f.value.password).subscribe((response) => {
        if (response != null) {
          this.user = response;
          console.log(this.user);
          sessionStorage.setItem("user", JSON.stringify(this.user));

          //const aside = this.getActiveItemAside();
          //if (aside) {
            // override aside menu as secondary menu of current header menu
        if(response.type == 0)
        {
            this.menuConfigService.configModel.config.aside = this.menuConfigService.configModel.config.aside_owner;
            this.menuConfigService.setModel(this.menuConfigService.configModel);
            //non torno al menu di default quando clicco i link
            this.menuConfigService.menuHasChanged = false;
            this.router.navigateByUrl("/managementPark");
        }

        if(response.type == 1)
        {
            this.menuConfigService.configModel.config.aside = this.menuConfigService.configModel.config.aside_driver;
            this.menuConfigService.setModel(this.menuConfigService.configModel);
            //non torno al menu di default quando clicco i link
            this.menuConfigService.menuHasChanged = false;
            this.router.navigateByUrl("/findCarPlace");
        }
        
          //}

          //if(response.type == 1)
          //this.router.navigate(['/']);
          //this.router.navigate(['/homeDriver']);
          //this.router.navigateByUrl("/homeDriver");
          //this.router.navigateByUrl("/");
          //else if(response.type == 0)
          //this.router.navigate(['/']);
          //this.router.navigate(['/homeOwner']);
          //this.router.navigateByUrl("/homeOwner");
          //this.router.navigateByUrl("/");

          this.cdr.detectChanges();
        } else
            this.feedback = "Username o password errati";
      });
  }


}
