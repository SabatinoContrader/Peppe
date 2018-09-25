import {
    Component,
    ComponentFactoryResolver,
    OnInit,
    ViewChild,
    ViewContainerRef,
    ViewEncapsulation,
    ChangeDetectorRef,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ScriptLoaderService } from '../_services/script-loader.service';
import { AuthenticationService } from './_services/authentication.service';
import { AlertService } from './_services/alert.service';
import { UserService } from './_services/user.service';
import { AlertComponent } from './_directives/alert.component';
import { LoginCustom } from './_helpers/login-custom';
import { Helpers } from '../helpers';
import { NgForm } from '@angular/forms';
import { UserService as UService } from '../_services/user.service';
import { User } from './_models/User';
// import { ApiService } from '../_services/';

@Component({
    selector: '.m-grid.m-grid--hor.m-grid--root.m-page',
    templateUrl: './templates/login-1.component.html',
    encapsulation: ViewEncapsulation.None,
})

export class AuthComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;

    feedback: string;
    user: User;

    @ViewChild('alertSignin',
        { read: ViewContainerRef }) alertSignin: ViewContainerRef;
    @ViewChild('alertSignup',
        { read: ViewContainerRef }) alertSignup: ViewContainerRef;
    @ViewChild('alertForgotPass',
        { read: ViewContainerRef }) alertForgotPass: ViewContainerRef;

    constructor(
        private _router: Router,
        private _script: ScriptLoaderService,
        private _userService: UserService,
        private _route: ActivatedRoute,
        private _authService: AuthenticationService,
        private _alertService: AlertService,
        private cfr: ComponentFactoryResolver,
        private userService: UService,
        private cdr: ChangeDetectorRef) {
    }

    ngOnInit() {
        this.model.remember = true;
        // get return url from route parameters or default to '/'
        var user = JSON.parse(localStorage.getItem("user"));
        var url = "";
        if(user) {
            url = user.type == 0 ? "/managementPark" : "/findCarPlace";
        }
        this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || url;
        this._router.navigate([this.returnUrl]);

        this._script.loadScripts('body', [
            'assets/vendors/base/vendors.bundle.js',
            'assets/demo/demo4/base/scripts.bundle.js'], true).then(() => {
                Helpers.setLoading(false);
                LoginCustom.init();
            });
    }

    // signin() {
    //     this.loading = true;
    //     this._authService.login(this.model.email, this.model.password).subscribe(
    //         data => {
    //             this._router.navigate([this.returnUrl]);
    //         },
    //         error => {
    //             this.showAlert('alertSignin');
    //             this._alertService.error(error);
    //             this.loading = false;
    //         });
    // }

    signin(f: NgForm): void{
        this.loading = true;
        this.userService.login(this.model.username, this.model.password).subscribe((response: any) => {
            if (response != null) {
                this.user = response;
                console.log(this.user);
                sessionStorage.setItem("user", JSON.stringify(this.user));
    
                //const aside = this.getActiveItemAside();
                //if (aside) {
                // override aside menu as secondary menu of current header menu
                if(response.type == 0)
                {
                    //   this.menuConfigService.configModel.config.aside = this.menuConfigService.configModel.config.aside_owner;
                    //   this.menuConfigService.setModel(this.menuConfigService.configModel);
                    //   //non torno al menu di default quando clicco i link
                    //   this.menuConfigService.menuHasChanged = false;
                    this._router.navigateByUrl("/managementPark");
                }
    
                if(response.type == 1)
                {
                    //   this.menuConfigService.configModel.config.aside = this.menuConfigService.configModel.config.aside_driver;
                    //   this.menuConfigService.setModel(this.menuConfigService.configModel);
                    //   //non torno al menu di default quando clicco i link
                    //   this.menuConfigService.menuHasChanged = false;
                    this._router.navigateByUrl("/findCarPlace");
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
            } else {
                this.showAlert('alertSignin');
                this._alertService.error("Username o password errati");
                this.loading = false;
            }
        });
    }

    signup() {
        this.loading = true;
        this._userService.create(this.model).subscribe(
            data => {
                this.showAlert('alertSignin');
                this._alertService.success(
                    'Thank you. To complete your registration please check your email.',
                    true);
                this.loading = false;
                LoginCustom.displaySignInForm();
                this.model = {};
            },
            error => {
                this.showAlert('alertSignup');
                this._alertService.error(error);
                this.loading = false;
            });
    }

    forgotPass() {
        this.loading = true;
        this._userService.forgotPassword(this.model.email).subscribe(
            data => {
                this.showAlert('alertSignin');
                this._alertService.success(
                    'Cool! Password recovery instruction has been sent to your email.',
                    true);
                this.loading = false;
                LoginCustom.displaySignInForm();
                this.model = {};
            },
            error => {
                this.showAlert('alertForgotPass');
                this._alertService.error(error);
                this.loading = false;
            });
    }

    showAlert(target) {
        this[target].clear();
        let factory = this.cfr.resolveComponentFactory(AlertComponent);
        let ref = this[target].createComponent(factory);
        ref.changeDetectorRef.detectChanges();
    }
}