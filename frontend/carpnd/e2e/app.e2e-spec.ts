import { CarpndPage } from './app.po';

describe('carpnd App', function() {
  let page: CarpndPage;

  beforeEach(() => {
    page = new CarpndPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
