import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": DashboardComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        LayoutModule,
        FormsModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [
        DashboardComponent
    ],
    providers: [

    ]
})
export class DashboardModule { }





