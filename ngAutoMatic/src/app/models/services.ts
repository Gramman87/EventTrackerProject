export class Services {
  id: number;
  type: string;
  date: string | undefined;
  odometer: string | undefined;
  cost: number | undefined;

  constructor(
    id: number = 0,
    type: string = "",
    date?: string,
    odometer?: string,
    cost?: number
) {
    this.id = id;
    this.type = type;
    this.date = date;
    this.odometer = odometer;
    this.cost = cost;
  }


}
