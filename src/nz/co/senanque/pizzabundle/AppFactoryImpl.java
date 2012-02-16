package nz.co.senanque.pizzabundle;

import java.util.Locale;

import nz.co.senanque.perspectivemanager.App;
import nz.co.senanque.perspectivemanager.AppFactory;
import nz.co.senanque.perspectivemanager.Blackboard;
import nz.co.senanque.pizzaorder.instances.ItemType;
import nz.co.senanque.pizzaorder.instances.OrderItem;
import nz.co.senanque.pizzaorder.instances.Pizza;
import nz.co.senanque.vaadin.CommandExt;
import nz.co.senanque.vaadin.MaduraSessionManager;
import nz.co.senanque.vaadin.MaduraSessionManagerFactory;
import nz.co.senanque.vaadin.MenuItemPainter;
import nz.co.senanque.vaadin.SubmitButtonPainter;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * @author Roger Parkinson
 *
 */
public class AppFactoryImpl implements AppFactory, MessageSourceAware {
	
	private MessageSource m_messageSource;

	/* (non-Javadoc)
	 * @see nz.co.senanque.bundle1.AppFactory#createApp()
	 */
	@Override
	public App createApp(Blackboard blackboard)
	{
		final MaduraSessionManager maduraSessionManager = MaduraSessionManagerFactory.getMaduraSessionManager();		
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(m_messageSource);
		App ret = new App();
		final Layout layout = new Layout();
		layout.setBlackboard(blackboard);
		ret.setComponentContainer(layout);
		Pizza orderItem = new Pizza();
		orderItem.setItemType(ItemType.PIZZA);
		maduraSessionManager.getValidationSession().bind(orderItem);
		layout.setItemDataSource(new BeanItem<OrderItem>(orderItem));
		MenuBar menuBar = new MenuBar();
		final MenuBar.MenuItem edit = menuBar.addItem(messageSourceAccessor.getMessage("edit", "Edit"), null);
		MenuItem menuItem = edit.addItem(messageSourceAccessor.getMessage("save", "Save"), new CommandExt(){

			private static final long serialVersionUID = 7142024506317299918L;
			MenuItemPainter m_menuItemPainter = new SubmitButtonPainter();
			
			public void menuSelected(MenuItem selectedItem) {
				layout.save();
			}

			public MenuItemPainter getPainter() {
//				m_menuItemPainter.setProperties(layout.getProperties());
				return m_menuItemPainter;
			}

			public MaduraSessionManager getMaduraSessionManager() {
				return maduraSessionManager;
			}});
		maduraSessionManager.register(menuItem);
		maduraSessionManager.bind(menuItem, layout.getProperties());
		ret.setMenuBar(menuBar);
		return ret;
	}

	public void setMessageSource(MessageSource messageSource) {
		m_messageSource = messageSource;
		
	}

}
