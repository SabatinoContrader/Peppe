import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ManagementParkComponent } from './management-park.component';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { AgmCoreModule } from '@agm/core';
import { GoogleMapService } from '../../../../_services/google-map.service';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": ManagementParkComponent
            }
        ]
    }
];
@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
        LayoutModule,
        AgmCoreModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [
        ManagementParkComponent
    ],
    providers: [
        GoogleMapService
    ]
})
export class ManagementParkModule {

}