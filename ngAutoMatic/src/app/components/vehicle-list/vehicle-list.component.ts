import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vehicle } from 'src/app/models/vehicle';
import { VehicleService } from 'src/app/services/vehicle.service';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {
  title = "AutoMatic";
  selected: Vehicle | null = null;
  newVehicle: Vehicle = new Vehicle();
  editVehicle: Vehicle | null = null;
  vehicles: Vehicle[] = [];


  constructor(private vehicleSvc: VehicleService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    let idStr = this.route.snapshot.paramMap.get("id");
    if (!this.selected && idStr) {
      let id = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.vehicleSvc.show(id).subscribe({
          next: (vehicle) => {
            this.selected = vehicle;
          },
          error: (err) => {
            console.error("VehicleListComponent.ngOnInit(): invalid VehicleId");
            this.router.navigateByUrl("404");
          }
        });
      } else {
        this.router.navigateByUrl("404");
      }
    }
    this.reload();
  }

  reload() {
    this.vehicleSvc.index().subscribe({
      next: (vehicles) => {
        this.vehicles = vehicles;
      },
      error: (err) => {
        console.error("VehicleListComponent.reload(): Error retreiving todos");
        console.error(err);
      }
    });
  }

  getVehicles() {
    return this.vehicles.length;
  }

  displayVehicle(vehicle: Vehicle) {
    this.selected = vehicle;
  }

  setEditVehicle() {
    this.editVehicle = Object.assign({}, this.selected);
  }

  addVehicle(vehicle: Vehicle) {
    this.vehicleSvc.create(vehicle).subscribe({
      next: (vehicle) => {},
      error: (err) => {
        console.error("VehicleListComponent.addVehicle(): Error");
        console.error(err);
      }
    });
  }

  updateVehicle(vehicle: Vehicle) {
    this.vehicleSvc.update(vehicle).subscribe({
      next: (vehicle) => {
        this.editVehicle = null;
        this.selected = vehicle;
        this.reload();
      },
      error: (err) => {
        console.error("VehicleListComponent.update(): Error");
        console.error(err);
      }
    });
  }

  deleteVehicle(id: number) {
    this.vehicleSvc.destroy(id).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.error("VehicleListComponent.deleteVehicle(): Error");
        console.error(err);
      }
    });
  }

}
