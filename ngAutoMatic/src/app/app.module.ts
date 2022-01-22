import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { UserService } from './services/user.service';
import { ServicesService } from './services/services.service';
import { VehicleService } from './services/vehicle.service';
import { DatePipe } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [VehicleService, UserService, ServicesService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
