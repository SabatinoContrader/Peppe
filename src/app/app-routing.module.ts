import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
//import { LoginComponent } from './content/pages/components/login/login.component';

const routes: Routes = [
	// {
	// 	path: '', redirectTo: '/login', pathMatch: 'full'
	// },
	// {path: 'login', component: LoginComponent},
	{
		path: '',
		loadChildren: 'app/content/pages/pages.module#PagesModule'
	},
	{
		path: '**',
		redirectTo: '404',
		pathMatch: 'full'
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes, {useHash: true, onSameUrlNavigation: 'reload'})],
	exports: [RouterModule],
	declarations: []
})
export class AppRoutingModule {}
