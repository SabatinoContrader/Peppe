import { AuthenticationService } from '../../../../../core/auth/authentication.service';
import { ChangeDetectionStrategy, Component, ElementRef, HostBinding, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer, SafeStyle } from '@angular/platform-browser';
import { UserService } from '../../../../../core/services/user.service';
import { MenuConfigService } from '../../../../../core/services/menu-config.service';

@Component({
	selector: 'm-user-profile',
	templateUrl: './user-profile.component.html',
	changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserProfileComponent implements OnInit {
	@HostBinding('class')
	// tslint:disable-next-line:max-line-length
	classes = 'm-nav__item m-topbar__user-profile m-topbar__user-profile--img m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light';

	@HostBinding('attr.m-dropdown-toggle') attrDropdownToggle = 'click';

	//@Input() avatar: string = './assets/app/media/img/users/user4.jpg';
	@Input() avatar: string = 'https://maps.google.com/mapfiles/kml/shapes/parking_lot_maps.png';
	
	@Input() avatarBg: SafeStyle = '';

	@ViewChild('mProfileDropdown') mProfileDropdown: ElementRef;

	constructor (
		private router: Router,
		private authService: AuthenticationService,
		private sanitizer: DomSanitizer,

		private menuConfigService: MenuConfigService,
		private loginService: UserService
	) {}

	ngOnInit (): void {
		if (!this.avatarBg)
			this.avatarBg = this.sanitizer.bypassSecurityTrustStyle('url(./assets/app/media/img/misc/user_profile_bg.jpg)');
	}

	public logout () {
		//this.authService.logout(true);
		//this.classes = this.classes.replace('m-dropdown--open', '');

		this.menuConfigService.configModel.config.aside = this.menuConfigService.configModel.config.aside;
		this.menuConfigService.setModel(this.menuConfigService.configModel);
		//non torno al menu di default quando clicco i link
		this.menuConfigService.menuHasChanged = true;

		this.router.navigate(["/"]);
		//this.loginService.logout();
	}
}
