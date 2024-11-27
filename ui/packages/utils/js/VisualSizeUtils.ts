export function isMobile() {
  const width = window.innerWidth;
  return width < 640;
}

export function isPad() {
  const width = window.innerWidth;
  return width < 900 && width > 640;
}
