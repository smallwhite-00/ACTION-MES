package cn.action.modules.bas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkOrderDao;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.entity.Material;
import cn.action.modules.bas.entity.Order;
import cn.action.modules.bas.entity.WorkOrder;
import javafx.concurrent.Worker;

@Service
@Transactional(readOnly=true)
public class WorkOrderService extends CrudService<WorkOrderDao, WorkOrder>{
	@Autowired
	private BomService bomService;
	@Autowired
	private BomDetailService bomDetailService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * ���湤��
	 * @param workOrder
	 * @return
	 */
	public boolean saveAll(WorkOrder workOrder) {
		boolean flag=true;
		//�ж����޸Ļ�����ӵı���
		if(workOrder.getIsNewRecord()) {
			//1.����ж�
			flag=this.stockProcessing(workOrder);
			if(!flag) {
				return flag;
			}
			//2.������ʱ���޸Ĺ�����Ӧ�Ķ�����״̬ΪworkOrder
			Order order=orderService.get(workOrder.getOrder().getId());
			order.setStatus("workOrder");
			orderService.save(order);			
		}
		//3.���湤��
		if(workOrder.getActStartTime().equals("")) {
			workOrder.setActStartTime(null);
		}
		this.save(workOrder);
		return flag;
	}
	//ģ��wmsϵͳ�Ŀ�洦���߼�
	private boolean stockProcessing(WorkOrder workOrder) {
		//1.���ݹ����еĲ�Ʒ��ö�Ӧ��Bom����
		Bom tempBom=new Bom();
		tempBom.setProduct(workOrder.getProduct());
		//�����װ������Bom�����÷�����ù�����Ӧ��Bom����
		List<Bom> boms=bomService.findList(tempBom);
		//2.����bom�����ѯbom���鼯��
		BomDetail tempDetail=new BomDetail();
		tempDetail.setBom(boms.get(0));
		List<BomDetail> bomdetailList=bomDetailService.findList(tempDetail);
		//3.���������еĲ�Ʒ��Ӧ��bom���鼯��
		//����һ��Ϊ24����Ʒ,����������bagΪǰ׺���ǰ�װ��������������freshΪǰ׺��������
		int allNums=workOrder.getAmount()*24;//������������Ʒ�Ĵ���
		double nums=0;//��ź��������������������
		double curSum=0;//ĳ����ԭ������
		List<Material> curMaterialList=new ArrayList<Material>();//���ĳ���͵�ÿ����ԭ�ϵļ���
		for(BomDetail detail:bomdetailList) {
			//1.������϶�Ӧ��ԭ�ϵĿ��������ÿ����ԭ�ϵļ���
			Material m=new Material();
			m.setmType(detail.getmType());
			curSum=materialService.findSum(m);
			curMaterialList=materialService.findList(m);
			//2.�������ǰ�����������ϵ�����
			if(detail.getmType().indexOf("bag")>-1 || detail.getmType().indexOf("fresh")>-1) {
				nums=allNums*detail.getmNum();
			}
			//3.����������������������Աȣ�С�ڷ���false��������ھͿۼ�ԭ������
			if(curSum<nums) {
				return false;
			}
			//�ۼ�ԭ������
			for(Material temp:curMaterialList) {
				if(temp.getQuantity()>=nums) {
					temp.setQuantity(temp.getQuantity()-nums);
					materialService.save(temp);
					break;
				}
				
				nums=nums-temp.getQuantity();
				temp.setQuantity(0);
				materialService.save(temp);
			}			
		}
		return true;		
	}
	/**
	 * ɾ������
	 * @param workOrder
	 * @return
	 */
	public boolean deleteOrder(WorkOrder workOrder) {
		//״̬�ǡ�δ�������Ĺ�������ɾ��
		//�жϹ�����״̬
		if(workOrder.getState().equals("unreleased")) {
			//ɾ��
			this.delete(workOrder);
			//�޸Ķ�Ӧ�Ķ���״̬Ϊ���¶�����newOrder
			Order tempOrder=orderService.get(workOrder.getOrder());
			tempOrder.setStatus("newOrder");
			orderService.save(tempOrder);
			return true;
		}
		return false;
	}
}
