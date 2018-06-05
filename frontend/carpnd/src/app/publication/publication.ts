import { User } from '../user/user';
import { Vehicle } from '../vehicle/vehicle';

export class ChronologyInfo {
	calendarType: string;
	id: string
}

export class LocalDate {
	chronology: ChronologyInfo;
	era: string;
	dayOfYear: number;
	dayOfWeek: string;
	leapYear: boolean;
	month:  string;
	dayOfMonth: number;
	year: number;
	monthValue: number;
}

export class Location {
	id: number;
	adressName: string;
	adressNumber: string;
	geoLatitude: number;
	geoLongitude: number;
}

export class Days {
	disabledDays: LocalDate[];
	reservedDays: LocalDate[];
	id: number;
}

export class Publication {
	id: number;
	owner: User;
	vehicle: Vehicle;
	acquireLocation: Location;
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


export class Page<T> {
	beforeUrl: string;
	nextUrl: string;
	elementList: T[];
}
