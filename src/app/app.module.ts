import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { CenterLayoutComponent } from './components/center-layout/center-layout.component';
import { InputTextService } from './services/input-text.service';

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    CenterLayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [InputTextService],
  bootstrap: [AppComponent]
})
export class AppModule { }
