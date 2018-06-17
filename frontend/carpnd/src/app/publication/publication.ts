import { User } from '../user/user';
import { Vehicle } from '../vehicle/vehicle';

export class ChronologyInfo {
	calendarType: string;
	id: string
}

export class Location {
	id: number;
	adressName: string;
	geoLatitude: number;
	geoLongitude: number;
}

export class Days {
	disabledDays: number[][];
	reservedDays: number[][];
	id: number;
}

export class Publication {
	id: number;
	owner: User;
	vehicle: Vehicle;
	acquireLocation: Location;
	returnLocations: Location[];
	enabledDays: Days;
}

export class MoneyAndAmount {
	currency: string;
	amount: number;
}

export class CreatePublication {
	id: number;
	vehicle: Vehicle;
	acquireLocation: Location;
	returnLocations: Location[];
	enabledDays: Days;
	costPerHour: MoneyAndAmount;
}


export class ReserveParameters {
	reservationDays: number[][];
	returnLocation: number;
	publication: number;
	customer:number;
}

export class Page<T> {
	beforeUrl: string;
	nextUrl: string;
	elementList: T[];
}
