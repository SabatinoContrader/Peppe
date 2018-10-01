import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ExtensionStopsComponent } from './extension-stops.component';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { ExtensionStopsRowComponent } from '../extension-stops-row/extension-stops-row.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        "children": [
            {
                "path": "",
                "component": ExtensionStopsComponent
            }
        ]
    }
];
@NgModule({
    imports: [
        CommonModule, RouterModule.forChild(routes), LayoutModule,
        FormsModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [
        ExtensionStopsComponent,
        ExtensionStopsRowComponent
    ],
    providers: [

    ]
})
export class ExtensionStopsModule {

}