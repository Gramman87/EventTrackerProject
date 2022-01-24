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
import { VehicleListComponent } from './components/vehicle-list/vehicle-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ServicesListComponent } from './components/services-list/services-list.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthService } from './services/auth.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    VehicleListComponent,
    ServicesListComponent,
    NotFoundComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [VehicleService, UserService, ServicesService, DatePipe, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
