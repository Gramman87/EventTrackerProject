export class Vehicle {
  id: number;
  vin: string | undefined;
  make: string | undefined;
  model: string | undefined;
  year: number | undefined;
  color: string | undefined;


  constructor(
    id: number = 0,
    vin?: string,
    make?: string,
    model?: string,
    year?: number,
    color?: string
) {
    this.id = id;
    this.vin = vin;
    this.make = make;
    this.model = model;
    this.year = year;
    this.color = color;
  }


}
