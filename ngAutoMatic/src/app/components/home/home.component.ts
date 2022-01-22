import { Component, OnInit } from '@angular/core';
import { ServicesService } from 'src/app/services/services.service';
import { UserService } from 'src/app/services/user.service';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private vehicleSvc: VehicleService,
    private userSvc: UserService,
    private servSvc: ServicesService
  ) { }

  ngOnInit(): void {
  }

}
