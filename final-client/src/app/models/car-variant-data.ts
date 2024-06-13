import { CarEngineData } from "./car-engine.data";

export interface CarVariantData{
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
}