import { CarEngineData } from "./car-engine.data";
import {PlpData} from "./plp.data";

export interface CarVariantData{
  id: number;

  color: string;

  year: number;

  kilometrage: string;

  transmission: string;

  drivetrain: string;

  price: string;

  numberOfDoors: number;

  numberOfSeats: number;

  safetyRating: string;

  carEngineResponse: CarEngineData;

  car: PlpData;
}
