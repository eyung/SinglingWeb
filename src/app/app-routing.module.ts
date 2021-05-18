import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CenterLayoutComponent } from './components/center-layout/center-layout.component';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';

const routes: Routes = [
  { path: '', component: MainLayoutComponent, children: [
    { path: '', component:  CenterLayoutComponent} 
  ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
