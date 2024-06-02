package it.epicode;

public interface Mapper  <D, S>  {
    S map(D input);
}
