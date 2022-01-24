import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Services } from 'src/app/models/services';
import { ServicesService } from 'src/app/services/services.service';

@Component({
  selector: 'app-services-list',
  templateUrl: './services-list.component.html',
  styleUrls: ['./services-list.component.css']
})
export class ServicesListComponent implements OnInit {
  selected: Services | null = null;
  newService: Services = new Services();
  editService: Services | null = null;
  services: Services[] = [];

  constructor(
    private servicesSvc: ServicesService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    let idStr = this.route.snapshot.paramMap.get("id");
    if (!this.selected && idStr) {
      let id = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.servicesSvc.show(id).subscribe({
          next: (service) => {
            this.selected = service;
          },
          error: (err) => {
            console.error("ServicesListComponent.ngOnInit(): invalid serviceId");
            this.router.navigateByUrl("404");
          }
        });
      } else {
        this.router.navigateByUrl("404");
      }
    }
  }

  reload() {
    this.servicesSvc.index().subscribe({
      next: (services) => {
        this.services = services;
      },
      error: (err) => {
        console.error("ServicesListComponent.reload(): Error retrieving services");
        console.error(err);
      }
    });
  }

  getServices() {
    return this.services.length;
  }

  displayService(service: Services) {
    this.selected = service;
  }

  setEditService() {
    this.editService = Object.assign({}, this.selected);
  }

  addService(service: Services) {
    this.servicesSvc.create(service).subscribe({
      next: (service) => {
        this.newService = new Services();
      },
      error: (err) => {
        console.error("SservicesListComponent.addService(): Error creating service");
        console.error(err);
      }
    });
  }

  updateService(service: Services) {
    this.servicesSvc.update(service).subscribe({
      next: (service) => {},
      error: (err) => {
        console.error("ServicesListComponent.update(): Error updating");
        console.error(err);
      }
    });
  }

  deleteService(id: number) {
    this.servicesSvc.destroy(id).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.error("ServicesListComponent.deleteService(): Error deleting service");
        console.error(err);
      }
    });
  }

}
