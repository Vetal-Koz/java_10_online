import { CarVariantData } from "./car-variant-data";

export interface PdpData{
  id: number,

  brand: string,
  
  model: string,

  description: string,

  generation: string,

  bodyType: string,

  carImages: string[],

  carVariantResponses: CarVariantData[],

}