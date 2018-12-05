import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ExtensionStopsRowComponent } from './extension-stops-row.component';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';

const routes: Routes = [
    {
        "path": "",
        "component": AsideLeftDisplayDisabledComponent,
        
    }
];
@NgModule({
    imports: [
        CommonModule, RouterModule.forChild(routes), LayoutModule
    ],
    exports: [
        RouterModule
    ],
    declarations: [
        
    ],
    providers: [

    ]
})
export class ExtensionStopsRowModule {

}